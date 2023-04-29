defmodule Program do
  def load({:prgrm, prgrm}) do
    mem = %{}
    mem = find_labels(mem, prgrm, 0)
    {prgrm, mem}
  end

  def read_instructions({:code, code}, pc) do
    elem(code, pc / 4)
  end

  def find_labels(mem, [{:label, label} | tail], pc) do
    write_data(mem, label, pc)
    find_labels(mem, tail, pc + 4)
  end

  def find_labels(mem, [_ | tail], pc) do
    find_labels(mem, tail, pc + 4)
  end

  def find_labels(mem, [], pc) do
    mem
  end

  def read_data(mem, i) do
    result = Map.fetch(mem, i)
    case result do
      :error -> 0
      {:ok, r} -> r
    end
  end

  def write_data(mem, i, data) do
    Map.put(mem, i, data)
  end
end
