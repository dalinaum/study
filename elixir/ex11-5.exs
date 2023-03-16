defmodule MyCharList do
  def max_length([]), do: 0

  def max_length([head | tail]) do
    l1 = max_length(tail)
    l2 = String.length(head)
    if l1 > l2, do: l1, else: l2
  end

  def center(list), do: _center(list, max_length(list))

  defp _center([], _max_length) do
  end

  defp _center([head | tail], max_length) do
    length = String.length(head)
    padding = div(max_length - length, 2)
    new_length = padding + length
    IO.puts(String.pad_leading(head, new_length))
    _center(tail, max_length)
  end
end
