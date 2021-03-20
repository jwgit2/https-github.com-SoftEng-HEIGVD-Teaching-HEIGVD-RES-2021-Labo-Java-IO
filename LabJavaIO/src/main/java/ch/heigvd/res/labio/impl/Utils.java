package ch.heigvd.res.labio.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
    int bsn = lines.indexOf('\n') + 1; // je récupère la position du 1er \n (+1 pour récupérer aussi l'échappement)
    int bsr = lines.indexOf('\r') + 1; // je récupère la position du 1er \r
    if (bsr != 0) // si j'ai au moins 1 \r
      if (bsr < (bsn - 1) || bsn == 0) // si j'ai un \r avant un \n ou si j'ai pas de \n
        bsn = bsr;


    return new String[]{lines.substring(0, bsn), lines.substring(bsn)};
  }

}
