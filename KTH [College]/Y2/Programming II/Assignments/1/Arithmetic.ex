defmodule Arithmetic do
  def add(a, b) do
    a + b
  end

  def subtract(a, b) do
    a - b
  end

  def multiply(a, b) do
    a * b
  end

  def divide(a, b) do
    a / b
  end

  def pow(_, 0) do 1 end

  def pow(a, b) do
    a * pow(a, b-1)
  end
end
