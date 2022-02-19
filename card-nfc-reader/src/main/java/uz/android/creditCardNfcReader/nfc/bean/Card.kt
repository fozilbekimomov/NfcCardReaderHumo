/* NFC Reader is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 3 of the License, or
(at your option) any later version.

NFC Reader is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Wget.  If not, see <http://www.gnu.org/licenses/>.

Additional permission under GNU GPL version 3 section 7 */
package uz.android.creditCardNfcReader.nfc.bean

class Card : uz.android.creditCardNfcReader.nfc.bean.Application() {
    private val applications: LinkedHashMap<Any, uz.android.creditCardNfcReader.nfc.bean.Application>
    var cardNumber: String? = null
        private set
    var cardExpiredDate: String? = null
        private set
    val readingException: Exception
        get() = getProperty(uz.android.creditCardNfcReader.nfc.SPEC.PROP.EXCEPTION) as Exception

    fun hasReadingException(): Boolean {
        return hasProperty(uz.android.creditCardNfcReader.nfc.SPEC.PROP.EXCEPTION)
    }

    val isUnknownCard: Boolean
        get() = applicationCount() == 0

    fun applicationCount(): Int {
        return applications.size
    }

    fun getApplications(): Collection<uz.android.creditCardNfcReader.nfc.bean.Application> {
        return applications.values
    }

    fun addApplication(app: uz.android.creditCardNfcReader.nfc.bean.Application?) {
        if (app != null) {
            val id = app.getProperty(uz.android.creditCardNfcReader.nfc.SPEC.PROP.ID)
            if (id != null && !applications.containsKey(id)) applications[id] = app
        }
    }

    //    public String toHtml() {
    //        return HtmlFormatter.formatCardInfo(this);
    //    }
    val serial: String
        get() {
            val ret = StringBuilder()
            val apps = getApplications()
            for (app in apps) {
                ret.append("\n")
                ret.append("---------------------------------------------")
                ret.append("\n")
                ret.append("Card number: ")
                ret.append(app.getStringProperty(uz.android.creditCardNfcReader.nfc.SPEC.PROP.SERIAL))
                ret.append("\n")
                ret.append("Card date: ")
                ret.append(app.getStringProperty(uz.android.creditCardNfcReader.nfc.SPEC.PROP.DATE))
                ret.append("\n")
                ret.append("---------------------------------------------")
                ret.append("\n")
            }
            return ret.toString()
        }
    val serialAndDate: Card
        get() {
            val card = Card()
            val apps = getApplications()
            for (app in apps) {
                card.cardNumber =
                    app.getStringProperty(uz.android.creditCardNfcReader.nfc.SPEC.PROP.SERIAL)
                card.cardExpiredDate =
                    app.getStringProperty(uz.android.creditCardNfcReader.nfc.SPEC.PROP.DATE)
            }
            return card
        }

    companion object {
        val EMPTY = Card()
    }

    init {
        applications = LinkedHashMap(2)
    }
}