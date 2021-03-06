package eu.isas.peptideshaker.export;

import com.compomics.util.experiment.biology.genes.GeneMaps;
import com.compomics.util.experiment.identification.Advocate;
import com.compomics.util.experiment.identification.Identification;
import com.compomics.util.experiment.identification.IdentificationKeys;
import com.compomics.util.experiment.io.biology.protein.ProteinDetailsProvider;
import com.compomics.util.experiment.io.biology.protein.SequenceProvider;
import com.compomics.util.waiting.WaitingHandler;
import com.compomics.util.parameters.identification.IdentificationParameters;
import eu.isas.peptideshaker.preferences.DisplayParameters;
import com.compomics.util.gui.filtering.FilterParameters;
import eu.isas.peptideshaker.parameters.PeptideShakerParameters;
import eu.isas.peptideshaker.preferences.ProjectDetails;
import com.compomics.util.parameters.quantification.spectrum_counting.SpectrumCountingParameters;
import eu.isas.peptideshaker.scoring.PSMaps;
import com.compomics.util.experiment.identification.features.IdentificationFeaturesCache;
import com.compomics.util.experiment.identification.peptide_shaker.Metrics;
import com.compomics.util.experiment.identification.protein_inference.fm_index.FMIndex;
import com.compomics.util.io.IoUtil;
import com.compomics.util.parameters.peptide_shaker.ProjectType;
import java.io.*;

/**
 * This class exports a PeptideShaker project as pdsb file.
 *
 * @author Marc Vaudel
 */
public class PsdbExporter {

    /**
     * Saves the given data in a psdb file.
     *
     * @param destinationFile the destination psdb file
     * @param waitingHandler a waiting handler used to cancel the saving
     * @param identification the identification to save
     * @param sequenceProvider the sequence provider
     * @param identificationParameters the identification parameters
     * @param proteinDetailsProvider the protein details providers
     * @param spectrumCountingParameters the spectrum counting preferences
     * @param projectDetails the project details
     * @param filterParameters the filtering preferences
     * @param displayParameters the display preferences
     * @param metrics the dataset
     * @param projectType the project type
     * @param geneMaps the gene maps
     * @param identificationFeaturesCache the identification features cache
     * @param emptyCache a boolean indicating whether the object cache should be
     * emptied
     * @param dbFolder the path to the folder where the database is located
     *
     * @throws IOException thrown whenever an error occurred while reading or
     * writing a file
     */
    public static void saveAs(
            File destinationFile,
            WaitingHandler waitingHandler,
            Identification identification,
            IdentificationParameters identificationParameters,
            SequenceProvider sequenceProvider,
            ProteinDetailsProvider proteinDetailsProvider,
            SpectrumCountingParameters spectrumCountingParameters,
            ProjectDetails projectDetails,
            FilterParameters filterParameters,
            Metrics metrics,
            GeneMaps geneMaps,
            ProjectType projectType,
            IdentificationFeaturesCache identificationFeaturesCache,
            boolean emptyCache,
            DisplayParameters displayParameters,
            File dbFolder
    ) throws IOException {

        identificationFeaturesCache.setReadOnly(true);

        try {

            // save the user advocates
            projectDetails.setUserAdvocateMapping(Advocate.getUserAdvocates());

            // add all necessary data and parameters into the db for export
            if (!identification.contains(PeptideShakerParameters.KEY)) {

                PeptideShakerParameters peptideShakerParameters = new PeptideShakerParameters(
                        identificationParameters, 
                        spectrumCountingParameters,
                        projectDetails, 
                        filterParameters, 
                        displayParameters, 
                        metrics, 
                        sequenceProvider, 
                        proteinDetailsProvider, 
                        geneMaps, 
                        projectType, 
                        identificationFeaturesCache
                );

                identification.addObject(PeptideShakerParameters.KEY, peptideShakerParameters);

            }
            
            

            // add the identification keys
            if (!identification.contains(IdentificationKeys.KEY)) {
                identification.addObject(IdentificationKeys.KEY, identification.getIdentificationKeys());
            }

            PSMaps psMaps = new PSMaps();
            long psMapsIdentKey = psMaps.getParameterKey();

            if (!identification.contains(psMapsIdentKey)) {
                identification.addObject(psMapsIdentKey, identification.getUrParam(psMaps));
            }
            
            
            

            // save the cache and the database
            if (waitingHandler == null || !waitingHandler.isRunCanceled()) {
                
                // remove the FM index temporaly from the parameters set
                PeptideShakerParameters peptideShakerParameters = (PeptideShakerParameters)identification.retrieveObject(PeptideShakerParameters.KEY);          
                SequenceProvider tmpSequenceProvider = peptideShakerParameters.getSequenceProvider();
                ProteinDetailsProvider tmpProteinDetailsProvider = peptideShakerParameters.getProteinDetailsProvider();
                peptideShakerParameters.cleanProviders();
                
                identification.updateObject(PeptideShakerParameters.KEY, peptideShakerParameters);

                identification.getObjectsDB().lock(waitingHandler);
                IoUtil.copyFile(identification.getObjectsDB().getDbFile(), destinationFile);
                // @TODO: re-add when the zipping works
//                GzUtils.gzFile(
//                        identification.getObjectsDB().getDbFile(),
//                        destinationFile,
//                        false
//                );
                identification.getObjectsDB().unlock();
                
                
                peptideShakerParameters.setSequenceProvider(tmpSequenceProvider);
                peptideShakerParameters.setProteinDetailsProvider(tmpProteinDetailsProvider);
                identification.updateObject(PeptideShakerParameters.KEY, peptideShakerParameters);

            }

        } finally {

            // restore the project navigability
            identificationFeaturesCache.setReadOnly(false);

        }
    }
}
