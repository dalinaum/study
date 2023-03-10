defmodule WeatherHistory do
    def for_location([], _targe_loc), do: []

    def for_location([[time, targe_loc, temp, rain] | tail], targe_loc) do
      [[time, targe_loc, temp, rain] | for_location(tail, targe_loc)]
    end

    def for_location([_ | tail], targe_loc), do: for_location(tail, targe_loc)

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
