"""
Graph Coloring gives us a number of Vertices (V), and colors (m), as well as a set of Edges (E).
It returns true if all the following conditions are met:
  1) All Vertices have a color (can be repeated between vertices) from 1 to m
  2) No two connected vertices have the same color

The Casting Problem gives us a number of roles (n), number of scenes (s), number of actors (k), and a set of constraints.
The first set of constraints denotes which actors can play for each role (only one is to be picked)
The second set of constraints denotes which roles are needed for each scene (all roles must be included in that scene)
The problem returns true if all the following conditions are met:
  1) All scenes have their required roles cast
  2) No two roles played by the same actor are in the same scene
  3) All scenes must have at least two roles in them
  4) Actor 1 and Actor 2 must each be casted at least once
  5) Actor 1 and Actor 2 must not be in any scenes together (they are the two Divas)

We are trying to karp reduce Graph Coloring into the Casting Problem. Therefore we must make sure our reduction gives
the same outputs as Graph Coloring will. In other words, we must come up with an input transformation between Graph Coloring
and the Casting Problem that ensures the conditions are either met for both problems, or not met for both problems.

In our solution, we will first ignore the Diva constraints (4) and (5) of the Casting Problem.
I will come back to why this doesn't matter later.

Now, we assign every vertex in Graph Coloring to a role in the Casting Problem, and the number of actors will be the number of colors.
Each edge in Graph Coloring will become a scene, where the two roles playing in the scene are the vertices attached to the edge.
- For condition (1) of the Casting Problem, all scenes will have their required roles cast if and only if every vertex gets
  assigned a color. This matches with condition (1) of Graph Coloring.
- For condition (2) of the Casting Problem, two roles with the same actor correspond to two vertices with the same color in Graph Coloring,
  and to roles in the same scene correspond to two connected vertices in graph coloring. Therefore condition (2) of the Casting Problem
  is satisfied if any only if condition (2) of Graph Coloring is also satisfied
- Condition (3) of the Casting Problem is always met given that a scene is an edge, and no loops are allowed which means an edge always has
  two vertices at its ends

Immediately we can see that if the diva constraints didn't exist, this would be all we have to do to reduce Graph Coloring to the
Casting Problem. Therefore, the easiest way to deal with them is to ensure they are always true.

Since the divas are by definition the first two actors in the list, we are going to follow the same steps as above, but assign each
Vertex to its original role number plus three. This is so we can account for two extra roles reserved for the divas alone, as well
as a role to assign to a placeholder actor. The purpose of the placeholder actor will soon be clear.

Of course, given these three new roles, we also have to assign three new actors, representing the divas and the placeholder.
We will right off the bat say, with the role constraints, that role 1 can only be played by actor 1, role 2 by actor 2, and role 3
by actor 3. This ensures that each role will be played by only the divas and the placeholder.

One small change we will make to our original transformation is the role constraints. Not only will the first three roles be reserved
for our special characters, but the rest of the roles will be reserved to everyone who is NOT either a diva or the placeholder.
This ensures the new divas and placeholder roles will not affect our original reduction proof.

If we now add two new scenes, one of which accepts diva 1's role and the placeholder role, and the second of which accepts
diva 2's role and the placeholder role, there is only one possible combination for these to be populated and it will always
satisfy constraints (4) and (5) of the Casting Problem. This is because the divas will always be casted exactly one in these
two scenes, and never with each other only with the placeholder. This is the necessity of the placeholder.

Therefore, we show that constraints (4) and (5) can always be met without affecting conditions (1), (2), and (3)
"""

V = int(input())
E = int(input())
m = int(input())

edges = [list(map(lambda x: int(x), (input()).split(" ")))
         for _ in range(0, E)]


def graph_coloring(V, E, m, edges):
    # Number of roles will be equal to the vertices + 2 roles for the divas, and 1 extra placeholder role
    print(V + 3)

    # Number of scenes will be equal to the edges + 2 scenes for the extra 3 roles
    # (placeholder plays with both divas in separate roles)
    print(E + 2)

    # Colors represent each actor + 2 divas and 1 placeholder actor
    print(m + 3)

    # Print out the roles that are guaranteed for the divas and the placeholder role
    print("1 1")
    print("1 2")
    print("1 3")

    # Print out the normal role constraints
    # All actors except the divas and the placeholder can play all roles
    for _ in range(0, V):
        line = f"{m} "

        for j in range(4, m+4):
            line += str(j) + " "

        print(line.rstrip())

    # Print out special scene constraints to satisfy diva requirement
    print(f"2 1 3")
    print(f"2 2 3")

    # Print out the normal scene constraints
    for edge in edges:
        print(f"2 {edge[0] + 3} {edge[1] + 3}")


graph_coloring(V, E, m, edges)
