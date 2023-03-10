defmodule WeatherHistory do
  def for_location_27([]), do: []

  def for_location_27([[time, 27, temp, rain] | tail]) do
    [[time, 27, temp, rain] | for_location_27(tail)]
  end

  def for_location_27([_ | tail]), do: for_location_27(tail)

  def test_data do
    [
      [1_366_225_622, 26, 15, 0.125],
      [1_366_225_622, 27, 15, 0.45],
      [1_366_225_622, 28, 21, 0.25],
      [1_366_225_622, 28, 21, 0.45],
      [1_366_229_222, 21, 20, 0.15],
      [1_366_229_222, 27, 20, 0.125],
      [1_366_222_822, 27, 21, 0.05],
      [1_366_222_822, 28, 24, 0.03],
      [1_366_222_822, 28, 27, 0.005],
      [1_366_222_822, 27, 24, 0.025],
      [1_366_223_322, 21, 21, 0.125],
      [1_366_123_322, 27, 21, 0.125],
      [1_366_123_322, 27, 27, 0.125]
    ]
  end
end
