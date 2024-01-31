defmodule Arithmetic do
  @type literal() ::
    {:num, number()} |
    {:var, atom()} |
    {:q, number(), number()}

  @type expr() ::
    {:add, expr(), expr()} |
    {:sub, expr(), expr()} |
    {:mul, expr(), expr()} |
    {:div, expr(), expr()} |
    literal()

  def new() do
    EnvTree.new()
  end

  def eval({:num, n}, _) do
    n
  end

  def eval({:q, n, m}, env) do
    divide(n, m)
  end

  def eval({:var, v}, env) do
    {:node, v, val, _, _} = EnvTree.lookup(env, v)
    val
  end

  def eval({:add, a, b}, env) do
    add(eval(a, env), eval(b, env))
  end

  def eval({:sub, a, b}, env) do
    sub(eval(a, env), eval(b, env))
  end

  def eval({:mul, a, b}, env) do
    mul(eval(a, env), eval(b, env))
  end

  def eval({:div, a, b}, env) do
    divide(eval(a, env), eval(b, env))
  end

  def add(e1, e2) do
    e1 + e2
  end

  def sub(e1, e2) do
    e1 - e2
  end

  def mul(e1, e2) do
    e1 * e2
  end

  def divide(e1, e2) do
    e1 / e2
  end
end
