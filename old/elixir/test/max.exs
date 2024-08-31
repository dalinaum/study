defmodule MyList do
  def max(list), do: max2(list, 0)

  def max2([], value), do: value
  def max2([head | tail], value) when head > value do
    max2(tail, head)
  end
  def max2([_head | tail], value), do: max2(tail, value)
end
