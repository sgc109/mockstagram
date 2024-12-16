package sean.hwang.mockstagram.reaction.api.util

import com.google.protobuf.BoolValue
import com.google.protobuf.ByteString
import com.google.protobuf.BytesValue
import com.google.protobuf.DoubleValue
import com.google.protobuf.FloatValue
import com.google.protobuf.Int32Value
import com.google.protobuf.Int64Value
import com.google.protobuf.StringValue
import com.google.protobuf.Timestamp
import com.google.protobuf.UInt32Value
import com.google.protobuf.UInt64Value
import java.math.BigDecimal
import java.time.Instant

fun StringValue.orElseNull(): String? = if (this === StringValue.getDefaultInstance()) {
    null
} else {
    this.value
}

fun Int32Value.orElseNull(): Int? = if (this === Int32Value.getDefaultInstance()) {
    null
} else {
    this.value
}

fun Int64Value.orElseNull(): Long? = if (this === Int64Value.getDefaultInstance()) {
    null
} else {
    this.value
}

fun UInt32Value.orElseNull(): Int? = if (this === UInt32Value.getDefaultInstance()) {
    null
} else {
    this.value
}

fun UInt64Value.orElseNull(): Long? = if (this === UInt64Value.getDefaultInstance()) {
    null
} else {
    this.value
}

fun FloatValue.orElseNull(): Float? = if (this === FloatValue.getDefaultInstance()) {
    null
} else {
    this.value
}

fun DoubleValue.orElseNull(): Double? = if (this === DoubleValue.getDefaultInstance()) {
    null
} else {
    this.value
}

fun BoolValue.orElseNull(): Boolean? = if (this === BoolValue.getDefaultInstance()) {
    null
} else {
    this.value
}

fun BytesValue.orElseNull(): ByteString? = if (this === BytesValue.getDefaultInstance()) {
    null
} else {
    this.value
}

fun StringValue.notNullValue() = requireNotNull(this.orElseNull())

fun Int32Value.notNullValue() = requireNotNull(this.orElseNull())

fun Int64Value.notNullValue() = requireNotNull(this.orElseNull())

fun UInt32Value.notNullValue() = requireNotNull(this.orElseNull())

fun UInt64Value.notNullValue() = requireNotNull(this.orElseNull())

fun FloatValue.notNullValue() = requireNotNull(this.orElseNull())

fun DoubleValue.notNullValue() = requireNotNull(this.orElseNull())

fun BoolValue.notNullValue() = requireNotNull(this.orElseNull())

fun BytesValue.notNullValue() = requireNotNull(this.orElseNull())

fun StringValue.orElse(defaultValue: String): String = if (this === StringValue.getDefaultInstance()) {
    defaultValue
} else {
    this.value
}

fun Int32Value.orElse(defaultValue: Int): Int = if (this === Int32Value.getDefaultInstance()) {
    defaultValue
} else {
    this.value
}

fun Int64Value.orElse(defaultValue: Long): Long = if (this === Int64Value.getDefaultInstance()) {
    defaultValue
} else {
    this.value
}

fun UInt32Value.orElse(defaultValue: Int): Int = if (this === UInt32Value.getDefaultInstance()) {
    defaultValue
} else {
    this.value
}

fun UInt64Value.orElse(defaultValue: Long): Long = if (this === UInt64Value.getDefaultInstance()) {
    defaultValue
} else {
    this.value
}

fun FloatValue.orElse(defaultValue: Float): Float = if (this === FloatValue.getDefaultInstance()) {
    defaultValue
} else {
    this.value
}

fun DoubleValue.orElse(defaultValue: Double): Double = if (this === DoubleValue.getDefaultInstance()) {
    defaultValue
} else {
    this.value
}

fun BoolValue.orElse(defaultValue: Boolean): Boolean = if (this === BoolValue.getDefaultInstance()) {
    defaultValue
} else {
    this.value
}

fun BytesValue.orElse(defaultValue: ByteString): ByteString = if (this === BytesValue.getDefaultInstance()) {
    defaultValue
} else {
    this.value
}

fun StringValue.toLongOrNull(): Long? = orElseNull()?.toLong()

fun StringValue.toIntOrNull(): Int? = orElseNull()?.toInt()

fun StringValue.toLong(): Long = requireNotNull(toLongOrNull())

fun StringValue.toInt(): Int = requireNotNull(toIntOrNull())

fun StringValue.toLongOrElse(defaultValue: Long = 0): Long = orElseNull()?.toLong() ?: defaultValue

fun StringValue.toIntOrElse(defaultValue: Int = 0): Int = orElseNull()?.toInt() ?: defaultValue

fun StringValue.toBigDecimalOrNull(): BigDecimal? = orElseNull()?.toBigDecimal()

fun StringValue.toBigDecimal(): BigDecimal = requireNotNull(toBigDecimalOrNull())

fun StringValue.toBigDecimalOrElse(defaultValue: BigDecimal): BigDecimal = toBigDecimalOrNull() ?: defaultValue

fun String.toStringValue(): StringValue = StringValue.newBuilder().setValue(this).build()

fun Int.toInt32Value(): Int32Value = Int32Value.newBuilder().setValue(this).build()

fun Int.toUint32Value(): UInt32Value = UInt32Value.newBuilder().setValue(this).build()

fun Int.toStringValue(): StringValue = StringValue.newBuilder().setValue(this.toString()).build()

fun Long.toInt64Value(): Int64Value = Int64Value.newBuilder().setValue(this).build()

fun Long.toUInt64Value(): UInt64Value = UInt64Value.newBuilder().setValue(this).build()

fun Long.toStringValue(): StringValue = StringValue.newBuilder().setValue(this.toString()).build()

fun Float.toFloatValue(): FloatValue = FloatValue.newBuilder().setValue(this).build()

fun Double.toDoubleValue(): DoubleValue = DoubleValue.newBuilder().setValue(this).build()

fun Boolean.toBoolValue(): BoolValue = BoolValue.newBuilder().setValue(this).build()

fun ByteString.toBytesValue(): BytesValue = BytesValue.newBuilder().setValue(this).build()

fun BigDecimal.toStringValue(): StringValue = StringValue.newBuilder().setValue(this.toPlainString()).build()

fun Instant.toTimestamp() = Timestamp.newBuilder().setSeconds(epochSecond).setNanos(nano).build()
