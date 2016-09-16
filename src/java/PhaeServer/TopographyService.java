/**
 * Used to track and update persistent topographic data in a 1024 x 1024 segment
 * of vertexes. This is functioning as a 2.5D map provider. It does, however,
 * assume that tile data is stored elsewhere as are the mechanical rules for
 * logically limiting the manipulation of this data.
 *
 * @author Matthew Jackson <mwj8410@gmail.com>
 */
public class TopographyService {

  // First dimension is y and the secon is x, and the third
  // is the actual topographic data
  private int[][][] chunkData  = new int[1024][1024][3];;

  // public void TerrainService() {
  //   chunkData = new int[1024][1024][3];
  // }

  /**
   *
   * @param x the cartesian side-to-side
   * @param y the cartesian up-and-down (not to be confused with vertical)
   * @return the topology data for the indicated vertex
   */
  public int[] getVertex (int x, int y) {
    return chunkData[y][x];
  }

  /**
   *
   * @param x the cartesian side-to-side
   * @param y the cartesian up-and-down (not to be confused with vertical)
   * @param tr the level for stone
   * @param tw the level for watertable
   * @param td the level for dirt
   * @param typographicData expected to be a 3 item int array
   */
  public void setVertex (int x, int y, int tr, int tw, int td) {
    chunkData[y][x][0] = tr;
    chunkData[y][x][1] = tw;
    chunkData[y][x][2] = td;
  }
}
