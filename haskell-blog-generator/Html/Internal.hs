module Html.Internal where

newtype Html
    = Html String

newtype Structure
    = Structure String

type Title
    = String

html_ :: Title -> Structure -> Html
html_ title content =
    Html
        (   el "html"
            (   el "head" (el "title" (escape title))
                <> el "body" (getStructureString content)
            )
        )

p_ :: String -> Structure
p_ = Structure . el "p" . escape

h1_ :: String -> Structure
h1_ = Structure . el "h1" .escape

pre_ :: String -> Structure
pre_ = Structure . el "pre" . escape

ul_ :: [Structure] -> Structure
ul_ =
    Structure . el "ul" . concat . map (el "li" . getStructureString)

ol_ :: [Structure] -> Structure
ol_ =
    Structure . el "ol" . concat . map (el "li" . getStructureString)

-- append_ :: Structure -> Structure -> Structure
-- append_ c1 c2 =
--     Structure (getStructureString c1 <> getStructureString c2)

instance Semigroup Structure where
    (<>) c1 c2 =
        Structure (getStructureString c1 <> getStructureString c2)

-- * Render

render :: Html -> String
render html =
    case html of
        Html str -> str

-- * Utilities

el :: String -> String -> String
el tag content =
    "<" <> tag <> ">" <> content <> "</" <> tag <> ">"

getStructureString :: Structure -> String
getStructureString content =
    case content of
        Structure str -> str

escape :: String -> String
escape =
    let
        escapeChar c =
            case c of
                '<' -> "&lt;"
                '>' -> "&gt;"
                '&' -> "&amp;"
                '"' -> "&quot;"
                '\'' -> "&#39;"
                _ -> [c]
    in
        concat . map escapeChar