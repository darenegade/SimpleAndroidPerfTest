package com.example.darenegade.perftest;

import fr.xebia.android.freezer.annotations.Id;
import fr.xebia.android.freezer.annotations.Model;

/**
 * Organization: HM FK07.
 * Project: PerfTest, com.example.darenegade.perftest
 * Author(s): Rene Zarwel
 * Date: 13.06.16
 * OS: MacOS 10.11
 * Java-Version: 1.8
 * System: 2,3 GHz Intel Core i7, 16 GB 1600 MHz DDR3
 */
@Model
public class Todo {
  @Id
  long id;
  String text;
  double lon;
  double lat;
}
