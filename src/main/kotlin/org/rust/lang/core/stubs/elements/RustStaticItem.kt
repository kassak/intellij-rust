package org.rust.lang.core.stubs.elements


import com.intellij.psi.stubs.IStubElementType
import com.intellij.psi.stubs.StubElement
import com.intellij.psi.stubs.StubInputStream
import com.intellij.psi.stubs.StubOutputStream
import org.rust.lang.core.psi.RustStaticItemElement
import org.rust.lang.core.psi.impl.RustStaticItemElementImpl
import org.rust.lang.core.stubs.RustNamedElementStub
import org.rust.lang.core.stubs.RustNamedStubElementType

object RustStaticItemStubElementType : RustNamedStubElementType<RustStaticItemElementStub, RustStaticItemElement>("STATIC_ITEM") {
    override fun createStub(psi: RustStaticItemElement, parentStub: StubElement<*>?): RustStaticItemElementStub =
        RustStaticItemElementStub(parentStub, this, psi.name, psi.isPublic)

    override fun createPsi(stub: RustStaticItemElementStub): RustStaticItemElement =
        RustStaticItemElementImpl(stub, this)

    override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): RustStaticItemElementStub =
        RustStaticItemElementStub(parentStub, this, dataStream.readNameAsString(), dataStream.readBoolean())

    override fun serialize(stub: RustStaticItemElementStub, dataStream: StubOutputStream) = with(dataStream) {
        writeName(stub.name)
        writeBoolean(stub.isPublic)
    }
}


class RustStaticItemElementStub(
    parent: StubElement<*>?,
    elementType: IStubElementType<*, *>,
    name: String?,
    isPublic: Boolean
) : RustNamedElementStub<RustStaticItemElement>(parent, elementType, name ?: "", isPublic)
