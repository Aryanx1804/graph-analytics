# Find Center of Star Graph

n = int(input("Enter number of edges: "))

a, b = map(int, input().split())

for i in range(n - 1):
    x, y = map(int, input().split())

    if x == a or x == b:
        center = x
    else:
        center = y

print("Center:", center)