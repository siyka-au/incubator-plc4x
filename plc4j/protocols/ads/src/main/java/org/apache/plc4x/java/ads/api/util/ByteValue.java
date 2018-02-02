/*
 Licensed to the Apache Software Foundation (ASF) under one
 or more contributor license agreements.  See the NOTICE file
 distributed with this work for additional information
 regarding copyright ownership.  The ASF licenses this file
 to you under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance
 with the License.  You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.
 */
package org.apache.plc4x.java.ads.api.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Objects;

public class ByteValue implements ByteReadable {

    final byte[] value;

    public ByteValue(byte... value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public void assertLength(int length) {
        if (value.length != length) {
            throw new IllegalArgumentException("Expected length " + length + " got " + value.length);
        }
    }

    public static void checkUnsignedBounds(long value, int numberOfBytes) {
        double upperBound = Math.pow(2, 8 * numberOfBytes);
        if (value < 0 || value >= upperBound) {
            throw new IllegalArgumentException("Value must between 0 and " + upperBound + ". Was " + value);
        }
    }

    @Override
    public byte[] getBytes() {
        return value;
    }

    @Override
    public ByteBuf getByteBuf() {
        return Unpooled.buffer().writeBytes(value);
    }
}
