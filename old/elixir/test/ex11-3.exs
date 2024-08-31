defmodule MyCharList do
  def is_printable([]), do: true

  def is_printable([head | tail]) do
    if head >= 32 and head <= 126 do
      is_printable(tail)
    else
      false
    end
  end

  def is_anagram(word1, word2) do
    Enum.sort(word1) == Enum.sort(word2)
  end

  def number([?- | tail]), do: _number_digits(tail, 0) * -1
  def number([?+ | tail]), do: _number_digits(tail, 0)
  def number(str), do: _number_digits(str, 0)

  defp _number_digits([], value), do: value

  defp _number_digits([digit | tail], value)
       when digit in '0123456789' do
    _number_digits(tail, value * 10 + digit - ?0)
  end

  defp _number_ditigs([non_digit | _tail], _value) do
    raise "Invalid digit '#{[non_digit]}'"
  end

  def calculate(str) do
    [v1, op, v2] = _words(str, [], [])
    first = number(v1)
    second = number(v2)

    if op == '+' do
      first + second
    else
      if op == '-' do
        first - second
      else
        if op == '*' do
          first * second
        else
          if op == '/' do
            first / second
          else
            raise "Invalid operator '#{op}'"
          end
        end
      end
    end
  end

  defp _words([], [], words), do: Enum.reverse(words)

  defp _words([], current, words) do
    Enum.reverse([Enum.reverse(current) | words])
  end

  defp _words([head | tail], current, words) do
    if head in ' ' do
      _words(tail, [], [Enum.reverse(current) | words])
    else
      _words(tail, [head | current], words)
    end
  end
end
