defmodule MyCharList do
  def is_printable([]), do: true

  def is_printable([head | tail]) do
    if (head >= 32 and head <= 126) do
      is_printable(tail)
    else
      false
    end
  end

  def is_anagram(word1, word2) do
    Enum.sort(word1) == Enum.sort(word2)
  end
end
