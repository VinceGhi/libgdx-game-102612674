package de.estim8.test.renderer.world;

/*
 * Why tf did i even add this? I am to lazy to create a map so i can't even check other chunks and therefor cant get rid of
 * of the vertices between blocks that are in different chunks.. i would also be happy enough to just not render the faces that are faced towards the chunk border
 * if there is no block...
 */
public enum BlockState {
    SOLID,
    NOT_SOLID,
    NULL
}
