defmodule MyList do
  def span(a, a), do: [a]
  def span(a, b) when a < b, do: [a | span(a + 1, b)]

  def prime(n) do
    for x <- span(2, n), x < 3 or Enum.all?(span(2, x - 1), &(rem(x, &1) != 0)), do: x
  end
end
