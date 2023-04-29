defmodule Emulator do
  def run(prgm) do
    {code, mem} = Program.load(prgm)
    reg = Register.new()
    out = Out.new()
    run(0, code, reg, mem, out)
  end

  def run(pc, code, reg, mem, out) do
    next = Program.read_instruction(code, pc)
    case next do
      :halt ->
        :ok

      {:add, rd, rs, rt} ->
        pc = pc + 4
        s = Register.read(reg, rs)
        t = Register.read(reg, rt)
        reg = Register.write(reg, rd, s + t)
        run(pc, code, reg, mem, out)

      {:sub, rd, rs, rt} ->
        pc = pc + 4
        s = Register.read(reg, rs)
        t = Register.read(reg, rt)
        reg = Register.write(reg, rd, s - t)
        run(pc, code, reg, mem, out)

      {:addi, rd, rt, imm} ->
        pc = pc + 4
        t = Register.read(reg, rt)
        reg = Register.write(reg, rd, t + imm)
        run(pc, code, reg, mem, out)

      {:lw, rd, rt, offset} ->
        pc = pc + 4
        t = Register.read(reg, rt)
        result = Program.read_data(mem, t + offset)
        Register.write(mem, rd, result)
        run(pc, code, reg, mem, out)

      {:sw, rs, rt, offset} ->
        pc = pc + 4
        s = Register.read(reg, rs)
        t = Register.read(reg, rt)
        mem = Program.write_data(mem, t + offset, s)
        run(pc, code, reg, mem, out)

      {:beq, rs, rt, {:label, label}} ->
        s = Register.read(reg, rs)
        t = Register.read(reg, rt)
        count = Program.read_data(mem, label)
        if s == t do run(count, code, reg, mem, out) else run(pc + 4, code, reg, mem, out) end

      {:out, rs} ->
        pc = pc + 4
        s = Register.read(reg, rs)
        out = Out.put(out, s)
        run(pc, code, reg, mem, out)

      {:label, label} ->
        pc = pc + 4
        run(pc, code, reg, mem, out)
    end
  end
end
