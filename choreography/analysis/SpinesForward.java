/* SpinesForward.java - Flips spines so the head is forwards when possible
 * Copyright 2010, 2011 Howard Hughes Medical Institute and Rex Kerr
 * This file is a part of Choreography and is distributed under the
 * terms of the GNU Lesser General Public Licence version 2.1 (LGPL 2.1).
 * For details, see http://www.gnu.org/licences
 */

import java.io.*;
import kerr.Vec.*;

public class SpinesForward implements CustomComputation
{
  Choreography chore = null;

  public SpinesForward() { }

  // Called at the end of command-line parsing, before any data has been read
  public void initialize(String args[],Choreography chore) throws IllegalArgumentException,IOException,CustomHelpException {
    this.chore = chore;
    for (String arg : args) {
      if (arg.toLowerCase().equals("help")) {
        System.out.println("Usage: --plugin SpinesForward");
        System.out.println("  SpinesForward aligns the first element of the spine with the head.");
        throw new CustomHelpException();
      }
    }
  }
  
  // Called before any method taking a File as an output target--this sets the extension
  public String desiredExtension() { return ""; }
  
  // Called on freshly-read objects to test them for validity (after the normal checks are done).
  public boolean validateDancer(Dance d) { return true; }
  
  // Called before any regular output is produced.  Returns true if it actually created the file.
  public boolean computeAll(File out_f) throws IOException {
    Vec2F u = new Vec2F();
    Vec2F v = new Vec2F();
    Vec2F w = new Vec2F();
    for (Dance d : chore.dances) {
      if (d==null) continue;
      if (d.spine==null) continue;
      if (d.segmentation==null) d.findSegmentation();
      d.findPostureConfusion();
      boolean[] confusion = new boolean[d.quantity.length];
      for (int i=0; i<confusion.length; i++) confusion[i] = d.quantity[i]>0;
      d.findDirectionBiasSegmented(chore.minTravel(d));
      int i = 0;
      int j = 0;
      for (; i<d.spine.length; i=j) {
        j = i;
        while (j<d.spine.length && (Float.isNaN(d.quantity[j]) || confusion[j])) j++;
        int k0 = j;
        while (j<d.spine.length && !(Float.isNaN(d.quantity[j]) || confusion[j])) j++;
        int k1 = j;
        while (j<d.spine.length && (Float.isNaN(d.quantity[j]) || confusion[j])) j++;
        if (j>k1+1 && j<d.spine.length) {
          float maxerr = 0.0f;
          int mei = k1;
          for (int k=k1;k<j-1;k++) {
            float derr = 0.0f;
            if (d.spine[k]==null || d.spine[k+1]==null) continue;
            for (int l=0; l<d.spine[k].size(); l++) {
              derr += d.spine[k].get(l,u).dist2(d.spine[k].get(l,v));
            }
            if (derr>maxerr) { maxerr = derr; mei = k; }
          }
          j = mei+1;
        }
        float o = 0.0f;
        for (int k=k0; k<k1; k++) {
          if (d.spine[k]==null) continue;
          d.spine[k].get(0,u).eqMinus(d.spine[k].get(d.spine[k].size()-1, v));
          d.getSegmentedDirection(k,w);
          if (u.dot(w)!=0.0f) o += u.unitDot(w)*d.quantity[k];
        }
        if (o<0) {
          for (int k=i;k<j;k++) if (d.spine[k]!=null) d.spine[k].flip();
        }
      }
    }

    return false;
  }
  
  // Also called before any regular output is produced (right after computeAll).  Returns true if it created a file.
  public boolean computeDancerSpecial(Dance d,File out_f) throws IOException { return false; }
  
  // Called when the C output option is given to figure out how many custom quantifications (output types) this plugin provides. 
  public int quantifierCount() { return 0; }
  
  // This is called whenever the plugin is required to handle a custom quantification.
  public void computeDancerQuantity(Dance d,int which) throws IllegalArgumentException {
    throw new IllegalArgumentException("SpinesForward quantifies nothing.");
  }
  
  // This is called when a custom quantification is graphed to provide a title for it.
  public String quantifierTitle(int which) throws IllegalArgumentException {
    throw new IllegalArgumentException("SpinesForward quantifies nothing.");
  }
};

