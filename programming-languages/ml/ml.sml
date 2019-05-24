Control.Print.printDepth := 100;
Control.Print.printLength := 100;

(* 1 *)
fun foo f g x = [g [f x]]


(* 2 *)
fun bar x [] = []
  | bar x (c::cs) = (x*c) :: bar x cs


(* 3 *)
fun part x [] = ([],[])
  | part x (c::cs) = let val (l,r) = part x cs
                     in if (c<x) then (c::l,r) else (l,c::r)
                     end


(* 4 *)
fun partSort [] = []
  | partSort [x] = [x]
  | partSort (x::xs) = let val (l,r) = part x xs
                       in partSort l @ [x] @ partSort r
                       end


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
          end


(* 6 *)
fun reduce f [] = let exception reduce_error in raise reduce_error end
  | reduce f [x] = x
  | reduce f (x::xs) = f x (reduce f xs)


(* 7 *)
datatype 'a tree = leaf of 'a | node of 'a tree list


(* 8 *)
fun fringe (leaf x) = [x]
  | fringe (node []) = []
  | fringe (node L) = reduce (fn L1 => fn L2 => L1 @ L2) (map fringe L)


(* 9 *)
fun sortTree (op <) (leaf x) = leaf (pSort (op <) x)
  | sortTree (op <) (node L) = node (map (sortTree (op <)) L)


(* 10 *)
fun powerSet [] = [[]]
  | powerSet (x::xs) = let val l = powerSet xs
                           fun appk k L = k::L
                       in map (appk x) l @ l
                       end



