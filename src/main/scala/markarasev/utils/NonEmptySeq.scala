package markarasev.utils

import scala.util.{Failure, Success, Try}

/**
  * Immutable representation of a non-empty data sequence.
  */
case class NonEmptySeq[+A](head: A, tail: Seq[A]) {

  /**
    * Transforms this [[NonEmptySeq]] instance into a `scala.immutable.Seq` one.
    */
  def toSeq: Seq[A] = head +: tail

}

object NonEmptySeq {

  /**
    * Constructs a [[NonEmptySeq]] instance from given elements.
    */
  def apply[A](elt: A, elts: A*): NonEmptySeq[A] = NonEmptySeq(elt, elts.to[Seq])

  /**
    * Attempts to construct a [[NonEmptySeq]] instance from given [[Seq]] instance.
    * @return a [[Failure]] with [[GivenSeqWasEmptyException]] instance if the
    *         given seq is empty, a [[Success]] with the [[NonEmptySeq]] instance
    *         otherwise
    */
  def fromSeq[A](seq: Seq[A]): Try[NonEmptySeq[A]] =
    seq.headOption.fold[Try[NonEmptySeq[A]]](
      Failure(GivenSeqWasEmptyException)
    )(
      head => Success(apply(head, seq.tail))
    )

  case object GivenSeqWasEmptyException extends IllegalArgumentException("given seq was empty")

}
