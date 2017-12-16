package markarasev.utils

import java.time.{ZoneOffset, ZonedDateTime}

/**
  * Immutable representation of a UTC zoned datetime.
  */
case class UtcDateTime private (u: ZonedDateTime) extends AnyVal

object UtcDateTime {

  /**
    * Constructs a UtcDateTime instance from current datetime.
    */
  def now(): UtcDateTime = apply()

  /**
    * Constructs a UtcDateTime instance from a given ZonedDateTime instance.
    */
  def from(date: ZonedDateTime): UtcDateTime = apply(date)

  private def apply(): UtcDateTime = UtcDateTime(ZonedDateTime.now())

  private def apply(date: ZonedDateTime): UtcDateTime = {
    val u = {
      if (date.getOffset != ZoneOffset.UTC)
        ZonedDateTime.ofInstant(date.toInstant, ZoneOffset.UTC)
      else date
    }
    new UtcDateTime(u)
  }

}
