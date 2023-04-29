defmodule Out do
  def new() do
    []
  end

  def put(list, item) do
    [item | list]
  end

  def print(list) do
    Enum.reverse(list)
  end
end
