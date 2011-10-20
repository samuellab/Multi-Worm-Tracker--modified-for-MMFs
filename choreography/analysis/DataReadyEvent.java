/* DataReadyEvent.java - Event used for transmitting data.
 * Copyright 2010 Howard Hughes Medical Institute and Nicholas Andrew Swierczek
 * This file is a part of Choreography and is distributed under the
 * terms of the GNU Lesser General Public Licence version 2.1 (LGPL 2.1).
 * For details, see http://www.gnu.org/licences
 */


import java.util.EventObject;

import kerr.Vec.*;


/**
 * Class representing a DataReady event. DataReady events are generated
 * by DataMapSources in cases where a requested image may not have
 * been created quick enough to display.
 * Contained within the Event are the data needed to generate the desired
 * image from a DataMapSource.
 * 
 * @author Nicholas Andrew Swierczek (swierczekn at janelia dot hhmi dot org)
 */
public class DataReadyEvent extends EventObject {
    public Vec2D data_position;
    
    public DataReadyEvent(Object source, Vec2D pos) {
        super(source);
        this.data_position = (pos==null) ? null : new Vec2D(pos);
    }
}
