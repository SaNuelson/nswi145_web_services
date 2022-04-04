07 - BPEL
===



## Notes to self

### Useful debugging techniques

- **Eclipse BPEL designer**
  - good for renaming stuff to ensure consistency
  - generating code in it is nice but tends to break and create unused variables and whatnot
  - offers no additional debugging

- **ode.log**
  - found in `path\to\tomcat\bin\ode.log`
  - filled during ODE's run
  - AFAIK offers the same info as bpelc ran with standard verbosity (useless e.g., for NullPointerException)

- **bpelc.bat**
  - compiler for `.bpel` files
  - run in cmd like: `path\to\ode\apache-ode-war-1.3.8\bin\bpelc.bat path\to\bpel\process.bpel`
  - additionally run with `-vv` if standard output offers no insight (e.g., NullPointerException)
  - it's a mess but the most useful tool

- **hot reload**
  - instead of restarting server, one can simply delete `myprocess.deployed` from the `processes/` dir
  - that forces ODE to reload that process and remove/readd in its GUI list

### Personal log

- breaks when link is defined using a (broken) linktype
  - works fine when broken linktype is declared but unused
  - logs don't show any errors