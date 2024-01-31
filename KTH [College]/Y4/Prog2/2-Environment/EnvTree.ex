defmodule EnvTree do
  def add(nil, key, value) do
    {:node, key, value, nil, nil}
  end

  def add({:node, key, _, left, right}, key, value) do
    {:node, key, value, left, right}
  end

  def add({:node, k, v, left, right}, key, value) when key < k do
     {:node, k, v, add(left, key, value), right}
  end

  def add({:node, k, v, left, right}, key, value) do
    {:node, k, v, left, add(right, key, value)}
  end

  def remove(nil, _) do
    nil
  end

  def remove({:node, key, _, nil, right}, key) do
    right
  end

  def remove({:node, key, _, left, nil}, key) do
    left
  end

  def remove({:node, key, _, left, right}, key) do
    {k, v} = leftmost(right)
    {:node, k, v, left, remove(right, key)}
  end

  def remove({:node, k, v, left, right}, key) when key < k do
    {:node, k, v, remove(left, key), right}
  end

  def remove({:node, k, v, left, right}, key) do
    {:node, k, v, left, remove(right, key)}
  end

  def lookup(nil, _) do
    nil
  end

  def lookup({:node, key, value, left, right}, key) do
    {:node, key, value, left, right}
  end

  def lookup({:node, k, _, left, _}, key) when key < k do
    lookup(left, key)
  end

  def lookup({:node, _, _, _, right}, key) do
    lookup(right, key)
  end

  def leftmost({:node, key, value, nil, _}) do
    {key, value}
  end

  def leftmost({:node, _, _, left, _}) do
    leftmost(left)
  end
end
