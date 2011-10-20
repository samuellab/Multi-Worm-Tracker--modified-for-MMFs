/* CustomComputation.java - Defines the standard plugin interface
 * Copyright 2010 Howard Hughes Medical Institute and Rex Kerr
 * This file is a part of Choreography and is distributed under the
 * terms of the GNU Lesser General Public Licence version 2.1 (LGPL 2.1).
 * For details, see http://www.gnu.org/licences
 */

import java.io.*;

public interface CustomComputation
{
  // Called at the end of command-line parsing, before any data has been read
  public void initialize(String args[],Choreography chore) throws 
    IllegalArgumentException,IOException,CustomHelpException;
  
  // Called before any method taking a File as an output target--this sets the extension
  public String desiredExtension();
  
  // Called on freshly-read objects to test them for validity (after the normal checks are done).
  public boolean validateDancer(Dance d);
  
  // Called before any regular output is produced.  Returns true if it actually created the file.
  public boolean computeAll(File out_f) throws IOException;
  
  // Also called before any regular output is produced (right after computeAll).  Returns true if it created a file.
  public boolean computeDancerSpecial(Dance d,File out_f) throws IOException;
  
  // Called when the C output option is given to figure out how many custom quantifications (output types) this plugin provides. 
  public int quantifierCount();
  
  // This is called whenever the plugin is required to handle a custom quantification.
  public void computeDancerQuantity(Dance d,int which) throws IllegalArgumentException;
  
  // This is called when a custom quantification is graphed to provide a title for it.
  public String quantifierTitle(int which) throws IllegalArgumentException;
};

