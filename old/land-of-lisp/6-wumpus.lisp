(load "5-list.lisp")

(defparameter *congestion-city-nodes* nil)
(defparameter *congestion-city-edges* nil)
(defparameter *visited-nodes* nil)
(defparameter *node-num* 30)
(defparameter *edge-num* 45)
(defparameter *worm-num* 3)
(defparameter *cop-odds* 15)

(defun random-node ()
  (1+ (random *node-num*)))

(defun edge-pair (a b)
  (unless (eql a b)
    (list (cons a b) (cons b a))))

;; (loop repeat 10 collect 1)
;; (1 1 1 1 1 1 1 1 1)

;; (loop for n from 1 to 10
;;       collect (+ 100 n))
;; (101 102 103 104 105 106 107 108 109 110)

(defun make-edge-list ()
  (apply #'append (loop repeat *edge-num*
			collect (edge-pair (random-node) (random-node)))))


(defun direct-edges (node edge-list)
  (remove-if-not (lambda (x)
		   (eql (car x) node))
		 edge-list))

(defun get-connected (node edge-list)
  (let ((visited nil))
    (labels ((traverse (node)
	       (unless (member node visited)
		 (push node visited)
		 (mapc (lambda (edge)
			 (traverse (cdr edge)))
		       (direct-edges node edge-list)))))
      (traverse node))
    visited))

;; let performs the bindings in parallel and let* does them sequentially.
;; set-difference returns a list of list-1 that do not appear in list-2.
;; (let ((a 5)
;;       (b (+ a 2)))
;;   b)
;; => EVAL: variable: A has no value
;; (let* ((a 5)
;;       (b (+ a 2)))
;;   b)
;; => 7
(defun find-islands (nodes edge-list)
  ;; (print `(nodes is ,nodes))
  ;; (print `(edge-list is ,edge-list))
  (let ((islands nil))
    (labels ((find-island (nodes)
	       (let* ((connected (get-connected (car nodes) edge-list))
		      (unconnected (set-difference nodes connected)))
		 ;; (print `(connected is ,connected))
		 ;; (print `(unconnected is ,unconnected))
		 (push connected islands)
		 (when unconnected
		   (find-island unconnected)))))
      (find-island nodes))
    islands))

(defun connect-with-bridges (islands)
  (when (cdr islands)
    (append (edge-pair (caar islands) (caadr islands))
	    (connect-with-bridges (cdr islands)))))

(defun connect-all-islands (nodes edge-list)
  (append (connect-with-bridges (find-islands nodes edge-list)) edge-list))

;; (equalp 3 3.0) => true
;; (equal 3 3.0) => false
(defun add-cops (edge-alist edges-with-cops)
  (mapcar (lambda (x)
	    (let ((node1 (car x))
		  (node1-edges (cdr x)))
	      (cons node1
		    (mapcar (lambda (edge)
			      (let ((node2 (car edge)))
				(if (intersection (edge-pair node1 node2)
						  edges-with-cops
						  :test #'equal)
				    (list node2 'cops)
				    edge)))
			    node1-edges))))
	  edge-alist))

(defun edges-to-alist (edge-list)
  (mapcar (lambda (node1)
	    (cons node1
		  (mapcar (lambda (edge)
			    (list (cdr edge)))
			  (remove-duplicates (direct-edges node1 edge-list)
					     :test #'equal))))
	  (remove-duplicates (mapcar #'car edge-list))))

(defun make-city-edges ()
  (let* ((nodes (loop for i from 1 to *node-num*
		      collect i))
	 (edge-list (connect-all-islands nodes (make-edge-list)))
	 (cops (remove-if-not (lambda (x)
				(zerop (random *cop-odds*)))
			      edge-list)))
    (print `(nodes is ,nodes))
    (print `(edge-list is ,edge-list))
    (print `(cops is ,cops))
    (add-cops (edges-to-alist edge-list) cops)))
