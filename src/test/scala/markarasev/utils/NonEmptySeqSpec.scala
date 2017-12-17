package markarasev.utils

import markarasev.utils.NonEmptySeq.GivenSeqWasEmptyException
import org.specs2.mutable.Specification

class NonEmptySeqSpec extends Specification {

  "Non-empty sequence object" should {

    "construct a non-empty sequence from a single element" in {
      val elt = 0
      val nes = NonEmptySeq(elt)
      nes.head shouldEqual elt
      nes.tail shouldEqual Seq.empty
    }

    "construct a non-empty sequence from several elements" in {
      val elt0 = 0
      val elt1 = 1
      val elt2 = 2
      val nes = NonEmptySeq(elt0, elt1, elt2)
      nes.head shouldEqual elt0
      nes.tail shouldEqual Seq(elt1, elt2)
    }

    "construct a non-empty sequence from a head element and a tail sequence" in {
      val head = 0
      val tail = Seq(1, 2)
      val nes = NonEmptySeq(head, tail)
      nes.head shouldEqual head
      nes.tail shouldEqual tail
    }

    "construct a NonEmptySeq instance from a non-empty scala.collection.immutable.Seq instance" in {
      val seq = Seq(0, 1, 2)
      val nesTry = NonEmptySeq.fromSeq(seq)
      nesTry should beSuccessfulTry
      val nes = nesTry.get
      nes.head shouldEqual seq.head
      nes.tail shouldEqual seq.tail
    }

    "not construct a NonEmptySeq instance from an empty scala.collection.immutable.Seq instance" in {
      val seq = Seq.empty
      val nesTry = NonEmptySeq.fromSeq(seq)
      nesTry should beAFailedTry
      val failure = nesTry.failed.get
      failure shouldEqual GivenSeqWasEmptyException
    }

  }

  "A non-empty sequence" should {

    "be used as a regular sequence" in {
      val elt0 = 0
      val elt1 = 1
      val elt2 = 2
      val nes = NonEmptySeq(elt0, elt1, elt2)
      val seq = nes.toSeq
      seq should not(beEmpty)
      seq shouldEqual Seq(elt0, elt1, elt2)
    }

  }

}
