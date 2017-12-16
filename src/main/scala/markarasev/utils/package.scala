package markarasev

package object utils {

  type Seq[+A] = scala.collection.immutable.Seq[A]
  val Seq: scala.collection.immutable.Seq.type = scala.collection.immutable.Seq

}
