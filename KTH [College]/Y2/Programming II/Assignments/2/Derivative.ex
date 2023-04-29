defmodule Derivative do
  # Constant
  def deriv({:num, _}, _) do
    {:num, 0}
  end

  # Itself
  def deriv({:var, x}, x) do
    {:num, 1}
  end

  # Itself with respect to another variable
  def deriv({:var, _}, _) do
    {:num, 0}
  end

  # Multiplication between two expressions
  def deriv({:mul, e1, e2}, x) do
    {:add, {:mul, deriv(e1, x), e2}, {:mul, deriv(e2, x), e1}}
  end

  # Addition between two expressions
  def deriv({:add, e1, e2}, x) do
    {:add, deriv(e1, x), deriv(e2, x)}
  end

  # Powers
  def deriv({:pow, {:var, x}, {:num, n}}, x) do
    {:mul, n, {:pow, x, (n - 1)}}
  end

  # Natural Logarithm
  def deriv({:ln, {:num, _}}, _) do
    {:num, 0}
  end

  def deriv({:ln, {:var, x}}, x) do
    {:pow, :x, -1}
  end

  # Square root
  def deriv({:sqrt, {:num, _}}, _) do
    {:num, 0}
  end

  def deriv({:sqrt, {:var, x}}, x) do
    {:pow, {:mul, 2, {:sqrt, x}}, -1}
  end

  def deriv({:sqrt, _}, _) do
    {:num, 0}
  end

  # Sine
  def deriv({:sin, {:num, _}}, _) do
    {:num, 0}
  end

  def deriv({:sin, {:var, x}}, x) do
    {:cos, x}
  end

  def deriv({:sin, _}, _) do
    {:num, 0}
  end
end
