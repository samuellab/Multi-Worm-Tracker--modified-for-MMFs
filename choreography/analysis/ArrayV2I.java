package kerr.Vec;

public class ArrayV2I {
  public final int[] data;
  public final int size;
  public int idx;
  public Vec2I v;
  
  public ArrayV2I(int[] ai) {
    data = ai;
    size = (ai==null) ? 0 : (ai.length/2);
    idx = 0;
    v = new Vec2I();
    if (size>0) {
      v.x = data[0];
      v.y = data[1];
    }
  }
  
  public Vec2I now() { return v; }
  public Vec2I next() {
    if (idx>=size) return null;
    else {
      idx += 1;
      if (idx>=size) return null;
      v.x = data[2*idx];
      v.y = data[2*idx+1];
      return v;
    }
  }
  public Vec2I prev() {
    if (idx<=0) return null;
    idx -= 1;
    v.x = data[2*idx];
    v.y = data[2*idx+1];
    return v;
  }
  public Vec2I at(int i) {
    idx = i;
    v.x = data[2*idx];
    v.y = data[2*idx+1];
    return v;
  }
  public Vec2I store() {
    data[2*idx] = v.x;
    data[2*idx+1] = v.y;
    return v;
  }
}

