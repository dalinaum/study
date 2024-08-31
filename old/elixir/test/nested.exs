defmodule Customer do
  defstruct name: "", company: ""
end

defmodule BugReport do
  defstruct owner: %Customer{}, details: "", severity: 1
end

# report = %BugReport{owner: %Customer{name: "Dave", company: "Pragmatic"}, details: "broken"}

# report = %BugReport{report | owner: %Customer{report.owner | company: "PragProg"}}

# put_in(report.owner.company, "PragProg")

# update_in(report.owner.name, &("Mr. " <> &1))
