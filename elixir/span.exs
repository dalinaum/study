defmodule MyList do
  def span(a, a), do: [a]
  def span(a, b) when a < b, do: [a | span(a + 1, b)]
end
