defmodule MyEnum do
  def all?([], _f), do: true

  def all?([head | tail], f) do
    if f.(head) do
      all?(tail, f)
    else
      false
    end
  end

  def each([], _f), do: []

  def each([head | tail], f) do
    [f.(head) | each(tail, f)]
  end

  def filter([], _f), do: []

  def filter([head | tail], f) do
    if f.(head) do
      [head | filter(tail, f)]
    else
      filter(tail, f)
    end
  end
end
