list_concat = fn a, b -> a ++ b end

sum = fn a, b, c -> a + b + c end

pair_tuple_to_list = fn {a, b} -> [a, b] end

fizz_buzz_string = fn
  0, 0, _ -> "FizzBuzz"
  0, _, _ -> "Fizz"
  _, 0, _ -> "Buzz"
  _, _, rest -> rest
end

fizz_buzz = fn
  n -> fizz_buzz_string.(rem(n, 3), rem(n, 5), n)
end
