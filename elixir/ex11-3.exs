defmodule MyCharList do
  def is_printable([]), do: true

  def is_printable([head | tail]) do
    if (head >= 32 and head <= 126) do
      is_printable(tail)
    else
      false
    end
  end
end
