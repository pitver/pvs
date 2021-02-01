package ru.vershinin.PVS.controller;

import java.util.Arrays;

/**
 * ControllerUtils
 *
 * @author Вершинин Пётр
 */
public class ControllerUtils {
    public static int[] merge(int[]... intarrays) {
        return Arrays.stream(intarrays).flatMapToInt(Arrays::stream)
                .toArray();
    }
}
