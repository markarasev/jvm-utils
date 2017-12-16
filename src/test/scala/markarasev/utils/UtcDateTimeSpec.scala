package markarasev.utils

import java.time.{ZoneOffset, ZonedDateTime}

import org.specs2.mutable.Specification

class UtcDateTimeSpec extends Specification {

  "UTC date time object" should {

    "construct the current date in UTC" in {
      val now = ZonedDateTime.now().toInstant.toEpochMilli
      val utcDate = UtcDateTime.now().u
      utcDate.getOffset shouldEqual ZoneOffset.UTC
      utcDate.toInstant.toEpochMilli should beCloseTo(now, 100)
    }

    "construct a UTC date given a UTC date" in {
      val zonedUtcDate = ZonedDateTime.now(ZoneOffset.UTC)
      val utcDate = UtcDateTime.from(zonedUtcDate)
      utcDate.u.getOffset shouldEqual ZoneOffset.UTC
      utcDate.u.toInstant shouldEqual zonedUtcDate.toInstant
    }

    "construct the equivalent UTC date given a non-UTC date" in {
      val nonUtcDate = ZonedDateTime.now(ZoneOffset.MAX)
      val utcDate = UtcDateTime.from(nonUtcDate)
      utcDate.u.getOffset shouldEqual ZoneOffset.UTC
      utcDate.u.toInstant shouldEqual nonUtcDate.toInstant
    }

  }

}
