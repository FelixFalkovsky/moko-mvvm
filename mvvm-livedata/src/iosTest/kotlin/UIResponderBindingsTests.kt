/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.bindBoolToControlEnabled
import dev.icerock.moko.mvvm.livedata.bindBoolToResponderFocus
import dev.icerock.moko.mvvm.livedata.bindBoolTwoWayToControlFocus
import dev.icerock.moko.mvvm.livedata.bindStringDescToButtonTitle
import dev.icerock.moko.mvvm.livedata.bindStringToButtonTitle
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import kotlinx.cinterop.readValue
import platform.CoreGraphics.CGRectZero
import platform.Foundation.NSDate
import platform.Foundation.NSRunLoop
import platform.Foundation.date
import platform.Foundation.runUntilDate
import platform.UIKit.UIButton
import platform.UIKit.UITextField
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class UIResponderBindingsTests {

    private lateinit var destination: UITextField

    @BeforeTest
    fun setup() {
        destination = UITextField(frame = CGRectZero.readValue())
    }

    // disabled while not found way to sync becomeFirstResponder logic with current thread
//    @Test
    fun `bool focused`() {
        val source: MutableLiveData<Boolean> = MutableLiveData(false)
        source.bindBoolToResponderFocus(destination)
        assertEquals(
            expected = false,
            actual = destination.focused
        )

        source.value = true
        NSRunLoop.currentRunLoop.runUntilDate(NSDate.date())
        assertEquals(
            expected = true,
            actual = destination.focused
        )
    }
}
