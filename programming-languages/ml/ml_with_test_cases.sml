Control.Print.printDepth := 100;
Control.Print.printLength := 100;

(* 1 *)
fun foo f g x = [g [f x]];


(* 2 *)
fun bar x [] = []
  | bar x (c::cs) = (x*c) :: bar x cs;

val g = bar 30;
g [1,2,3,4,5];
bar 30 [1,2,3,4,5];


(* 3 *)
fun part x [] = ([],[])
  | part x (c::cs) = let val (l,r) = part x cs
                     in if (c<x) then (c::l,r) else (l,c::r)
                     end;

part 6 [5,2,8,4,1,9,6,10];


(* 4 *)
fun partSort [] = []
  | partSort [x] = [x]
  | partSort (x::xs) = let val (l,r) = part x xs
                       in partSort l @ [x] @ partSort r
                       end;

partSort [5,2,9,10,12,4,8,1,19];


(* 5 *)
fun pSort (op <) [] = []
  | pSort (op <) [x] = [x]
  | pSort (op <) (x::xs) = 
          let fun pPart (op <) y [] = ([],[])
                | pPart (op <) y (c::cs) = 
                        let val (l,r) = pPart (op <) y cs
                        in if (c<y) then (c::l,r) else (l,c::r)
                        end
              val (l,r) = pPart (op <) x xs
          in pSort (op <) l @ [x] @ pSort (op <) r
          end;

pSort (op <) [1,9,3,6,7];
pSort (fn(a,b) => length a < length b) [[1, 9, 3, 6], [1], [2,4,6], [5,5]];


(* 6 *)
fun reduce f [] = let exception reduce_error in raise reduce_error end
  | reduce f [x] = x
  | reduce f (x::xs) = f x (reduce f xs);

fun g x y = x + y;
reduce g [1,2,3,4,5]; 
reduce (fn x => fn y => x + y) [1,2,3,4,5];
reduce g [3];


(* 7 *)
datatype 'a tree = leaf of 'a | node of 'a tree list;

val myTree = node [node [node [leaf [4,2,14],leaf [9,83,32],leaf [96,123,4]],
                         node [leaf [47,71,82]],node [leaf [19,27,10],
                                                      leaf [111,77,22,66]],
                         leaf [120,42,16]],
                   leaf [83,13]];


(* 8 *)
fun fringe (leaf x) = [x]
  | fringe (node L) = reduce (fn L1 => fn L2 => L1 @ L2) (map fringe L);

fringe (node [leaf 1,node [leaf 2,leaf 3],node [node [leaf 4,leaf 5],leaf 6]]);
fringe myTree;


(* 9 *)
fun sortTree (op <) (leaf x) = leaf (pSort (op <) x)
  | sortTree (op <) (node L) = node (map (sortTree (op <)) L);

sortTree (op <) myTree;


(* 10 *)
fun powerSet [] = [[]]
  | powerSet (x::xs) = let val l = powerSet xs
                           fun appk k L = k::L
                       in map (appk x) l @ l
                       end;

powerSet [1,2,3];










