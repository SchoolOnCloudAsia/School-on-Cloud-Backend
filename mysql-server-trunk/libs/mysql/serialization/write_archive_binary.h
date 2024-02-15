// Copyright (c) 2023, Oracle and/or its affiliates.
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License, version 2.0,
// as published by the Free Software Foundation.
//
// This program is also distributed with certain software (including
// but not limited to OpenSSL) that is licensed under separate terms,
// as designated in a particular file or component or in included license
// documentation.  The authors of MySQL hereby grant you an additional
// permission to link the program and your derivative works with the
// separately licensed software that they have included with MySQL.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License, version 2.0, for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301  USA.

#ifndef MYSQL_SERIALIZATION_WRITE_ARCHIVE_BINARY_H
#define MYSQL_SERIALIZATION_WRITE_ARCHIVE_BINARY_H

#include <sstream>

#include "mysql/serialization/archive.h"
#include "mysql/serialization/archive_binary.h"
#include "mysql/serialization/archive_binary_field_max_size_calculator.h"
#include "mysql/serialization/primitive_type_codec.h"
#include "mysql/serialization/serialization_types.h"

/// @file
/// Experimental API header

/// @addtogroup GroupLibsMysqlSerialization
/// @{

namespace mysql::serialization {

/// @brief Binary, write only archive implementation based on vector of bytes
class Write_archive_binary : public Archive<Write_archive_binary> {
 public:
  using Internal_type = unsigned char *;

  /// @copydoc Archive::operator<<
  template <typename Field_type>
  Write_archive_binary &operator<<(Field_type &&arg);

  /// @copydoc Archive::get_size
  template <typename Field_type>
  static std::size_t get_size(Field_type &&arg) {
    static constexpr auto value_size = Field_type::value_size;
    using value_type = typename std::decay_t<Field_type>::value_type;
    return Primitive_type_codec<value_type>::template count_write_bytes<
        value_size>(arg.get());
  }

  /// @copydoc Archive::get_max_size
  template <typename T, Field_size S>
  static constexpr std::size_t get_max_size() {
    return Archive_binary_field_max_size_calculator<T, S>::get_max_size();
  }

  /// @copydoc Archive::get_size_written
  std::size_t get_size_written() const;

  /// @brief Set up internal stream, this implementation of Archive does not
  /// own memory, it just reads it
  /// @param[in] stream Captured stream
  /// @param[in] stream_size Size of stream
  void set_stream(unsigned char *stream, std::size_t stream_size) {
    m_stream = stream;
    m_stream_size = stream_size;
  }

  // internal

  /// @copydoc Archive::get_raw_data
  Internal_type &get_raw_data();

  /// @copydoc Archive::peek_type_field_id
  Field_id_type peek_type_field_id();

 private:
  /// @brief Internal function used to check whether writing is possible
  /// @param bytes Requested number of bytes
  /// @retval true Writing of requested number of bytes is possible
  /// @retval false Cannot write requested number of bytes
  bool can_write(size_t bytes) const;

  Internal_type m_stream;        ///< Internal data stream
  std::size_t m_stream_size{0};  ///< Internal data stream size
  std::size_t m_write_pos{0};    ///< Write position
};

}  // namespace mysql::serialization

/// @}

#include "mysql/serialization/write_archive_binary_impl.hpp"

#endif  // MYSQL_SERIALIZATION_WRITE_ARCHIVE_BINARY_H
