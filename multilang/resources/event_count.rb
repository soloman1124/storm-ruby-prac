require './storm'
require 'json'

class EventCountBolt < Storm::Bolt
  def process tup
    event = JSON.parse tup.values[0]
    event_name = event['name']
    count = count_event event_name

    emit [event_name, count]
  end

  private

  def count_event event_name
    counter[event_name] = 0 unless counter.has_key? event_name

    counter[event_name] += 1
  end

  def counter
    @counter ||= Hash.new
  end
end

EventCountBolt.new.run
