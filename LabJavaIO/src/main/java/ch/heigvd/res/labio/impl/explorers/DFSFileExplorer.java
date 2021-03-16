
package ch.heigvd.res.labio.impl.explorers;

import ch.heigvd.res.labio.interfaces.IFileExplorer;
import ch.heigvd.res.labio.interfaces.IFileVisitor;

import java.io.File;
import java.util.Arrays;


/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {
    if (visitor != null && rootDirectory != null){
      visitor.visit(rootDirectory);
      // I'm quite happy that we don't have a lot of graph dev to do in embedded development
      File[] foldersList = rootDirectory.listFiles();
      // I get my folders right on
      if (foldersList != null) {
        // These tests ain't gonna let me go through this
        Arrays.sort(foldersList);
        // My folders are sorted yay
        for (File file : foldersList) {
          if (file.isDirectory()) {
            // Is there a subfolder there?
            explore(file, visitor);
            // Show me what u got
          } else {
            // Anyone? No?
            visitor.visit(file);
          }
        }
      }
    }
  }
}

