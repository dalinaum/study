defmodule MyList do
  def caesar('', _n), do: ''
  def caesar([head | tail], n) do
    x = head + n
    x = if x > 122 do
      x - 122 + 96
    else
      x
    end
    [x | caesar(tail, n)]
  end
end
