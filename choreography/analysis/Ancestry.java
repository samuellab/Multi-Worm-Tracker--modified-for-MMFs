/* Ancestry.java - Maintains history of how objects were found/lost
 * Copyright 2010 Howard Hughes Medical Institute and Rex Kerr
 * This file is a part of Choreography and is distributed under the
 * terms of the GNU Lesser General Public Licence version 2.1 (LGPL 2.1).
 * For details, see http://www.gnu.org/licences
 */

import java.util.*;
import kerr.Vec.*;

public class Ancestry
{
  public int frame;
  public Vector<Vec2I> orifates;
  public Ancestry(int frame_number) { frame = frame_number; orifates = new Vector<Vec2I>(4,2); }
  public void addOriginFate(int ori,int fat) { orifates.add( new Vec2I( ori , fat ) ); }
}

