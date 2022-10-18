(setf *print-circle* t)

(defparameter foo '(1 2 3))
(setf (cdddr foo) foo)

(defparameter *drink-order* '((bill . double-espresso)
			      (lisa . small-drip-coffee)
			      (john . medium-latte)))

(assoc 'lisa *drink-order*)

(push '(lisa . large-mocha-with-whipped-cream) *drink-order*)

(assoc 'lisa *drink-order*)

(defparameter *house* '((walls
			 (mortar (cement)
			  (water)
			  (sand))
			 (brics))
			(windows (glass)
			 (frame)
			 (curtains))
			(roof (shingles)
			 (chimney))))

(defparameter *wizard-nodes* '((living-room (you are in the living room.
					     a wizard is snoring loudly on the couch.))
			       (garden (you are in a beautiful garden.
					there is a well in front of you.))
			       (attic (you are in the attic. there
				       is a giant welding torch in the corner.))))

(defparameter *wizard-edges* '((living-room (garden west door)
				(attic upstairs ladder))
			       (garden (living-room east door))
			       (attic (living-room downstairs ladder))))

;; substitute-if newitem predicate sequence => result-sequence
(defun dot-name (exp)
  (substitute-if #\_ (complement #'alphanumericp) (prin1-to-string exp)))

(dot-name 'living-room)

(dot-name 'foo!)

(dot-name '24)

(substitute-if #\e #'digit-char-p "I'm a l33t hack3r!")

(substitute-if 0 #'oddp '(1 2 3 4 5 6 7 8))

(defparameter *max-label-length* 30)

(defun dot-label (exp)
  (if exp
      (let ((s (write-to-string exp :pretty nil)))
	(if (> (length s) *max-label-length*)
	    (concatenate 'string (subseq s 0 (- *max-label-length* 3)) "...")
	    s))
      ""))

;; mapc is like mapcar except that the results of applying function are
;; not accumulated. The list argument is returned.
(defun nodes->dot (nodes)
  (mapc (lambda (node)
	  (fresh-line)
	  (princ (dot-name (car node)))
	  (princ "[label=\"")
	  (princ (dot-label node))
	  (princ "\"];"))
	nodes))

(nodes->dot *wizard-nodes*)

(defun edges->dot (edges)
  (mapc (lambda (node)
	  (mapc (lambda (edge)
		  (fresh-line)
		  (princ (dot-name (car node)))
		  (princ "->")
		  (princ (dot-name (car edge)))
		  (princ "[label=\"")
		  (princ (dot-label (cdr edge)))
		  (princ "\"];"))
		(cdr node)))
	edges))

(edges->dot *wizard-edges*)

(defun graph->dot (nodes edges)
  (princ "digraph{")
  (nodes->dot nodes)
  (edges->dot edges)
  (princ "}"))

;; ext:shell is not supported by sbcl. So uiop:run-program is used instead.
;; funcall delays thuunk.
(defun dot->png (fname thunk)
  (with-open-file (*standard-output*
		   fname
		   :direction :output
		   :if-exists :supersede)
    (funcall thunk))
  (uiop:run-program (concatenate 'string "dot -Tpng -O " fname)))

(with-open-file (my-stream
		 "testfile.txt"
		 :direction :output
		 :if-exists :supersede)
  (princ "Hello File!" my-stream))

;; cigar is symbol. It can be assignable.
(let ((cigar 5))
  cigar)

;; :cigar is keyword symbol. It cannot be assignable.
;; (let ((:cigar 5))
;;   :cigar)

(defun graph->png (fname nodes edges)
  (dot->png fname
	    (lambda ()
	      (graph->dot nodes edges))))

(graph->png "wizard.dot" *wizard-nodes* *wizard-edges*)

;; maplist is like mapcar except that function is applied to successive
;; sublists of the lists. function is first applied to the lists themselves,
;; and then to the cdr of each list, and then to the cdr of the cdr of each
;; list, and so on.
;; eg. (maplist #'(lambda (x) (cons 'foo x)) '((a b c d) (f g h i)))
;; => ((FOO (A B C D) (F G H I)) (FOO (F G H I)))
;; (mapcar #'(lambda (x) (cons 'foo x)) '((a b c d) (f g h i)))
;; => ((FOO A B C D) (FOO F G H I))
(defun uedges->dot (edges)
  ;; (print `(uedges->dot edges ,edges))
  (maplist (lambda (lst)
	     ;; (print `(lst is ,lst))
	     (mapc (lambda (edge)
		     ;; (print `(edge is ,edge))
		     (fresh-line)
		     ;; (princ "assoc: ")
		     ;; (princ (assoc (car edge) (cdr lst)))
		     (unless (assoc (car edge) (cdr lst))
		       (fresh-line)
		       (princ (dot-name (caar lst)))
		       (princ "--")
		       (princ (dot-name (car edge)))
		       (princ "[label=\"")
		       (princ (dot-name (cdr edge)))
		       (princ "\"];")))
		   (cdar lst)))
	   edges))


(defun ugraph->dot (nodes edges)
  (princ "graph{")
  (nodes->dot nodes)
  (uedges->dot edges)
  (princ "}"))

(defun ugraph->png (fname nodes edges)
  (dot->png fname
	    (lambda()
	      (ugraph->dot nodes edges))))

(ugraph->png "uwizard.dot" *wizard-nodes* *wizard-edges*)
