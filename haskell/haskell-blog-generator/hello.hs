import Html

main :: IO ()
main = putStrLn (render myhtml)

myhtml :: Html
myhtml =
    html_
        "Hello title"
        (   h1_ "Hello, world!" <>
            (   (p_ "Paragraph #1") <>
                (p_ "Paragraph #2")
            )
        )
