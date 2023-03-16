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

  def capitalize_sentences(str), do: _capitalize_sentences(str, true, true, <<>>)

  def _capitalize_sentences(<<>>, _after_dot, _after_space, str), do: str

  def _capitalize_sentences(<<head::utf8, tail::binary>>, after_dot, after_space, str) do
    if head == ?. do
      str = <<str::binary, head::utf8>>
      _capitalize_sentences(tail, true, false, str)
    else
      if after_dot and head == 32 do
        str = <<str::binary, head::utf8>>
        _capitalize_sentences(tail, true, true, str)
      else
        if after_dot and after_space do
          head = head - ?a + ?A
          str = <<str::binary, head::utf8>>
          _capitalize_sentences(tail, false, false, str)
        else
          if head >= ?A and head <= ?Z do
            head = head - ?A + ?a
            str = <<str::binary, head::utf8>>
            _capitalize_sentences(tail, false, false, str)
          else
            str = <<str::binary, head::utf8>>
            _capitalize_sentences(tail, false, false, str)
          end
        end
      end
    end
  end

  def total_amount() do
    file = File.open!("tax.csv")
    IO.read(file, :line)

    file
    |> IO.stream(:line)
    |> Enum.map(fn str ->
      [str_id, str_ship_to, str_net_amount] = String.split(str, ",")
      {id, _} = Integer.parse(str_id)
      ship_to = str_ship_to |> String.replace(":", "") |> String.to_atom()
      {net_amount, _} = Float.parse(str_net_amount)
      [id: id, ship_to: ship_to, net_amount: net_amount]
    end)
    |> _total_amount
  end

  def _total_amount(orders) do
    tax_rates = [NC: 0.075, TX: 0.08]

    for order <- orders do
      if order[:ship_to] in Keyword.keys(tax_rates) do
        net_amount = order[:net_amount]
        tax_rate = tax_rates[order[:ship_to]]
        total_amount = net_amount + net_amount * tax_rate
        order = Keyword.put(order, :total_amount, total_amount)
      else
        order
      end
    end
  end
end
