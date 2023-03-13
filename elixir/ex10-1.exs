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

  def split(list, n), do: split([], list, n)

  def split(collection, [], _n) do
    [Enum.reverse(collection), []]
  end

  def split(collection, list, 0) do
    [Enum.reverse(collection), list]
  end

  def split(collection, [head | tail], n) do
    split([head | collection], tail, n - 1)
  end

  def flatten(list), do: flatten([], list)

  def flatten(collection, []), do: Enum.reverse(collection)

  def flatten(collection, [head | tail]) do
    if is_list(head) do
      flatten(collection, head ++ tail)
    else
      flatten([head | collection], tail)
    end
  end
end
